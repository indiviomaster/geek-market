package com.geekbrains.geekmarket.services;

import com.geekbrains.geekmarket.entities.Role;
import com.geekbrains.geekmarket.entities.SystemUser;
import com.geekbrains.geekmarket.entities.User;
import com.geekbrains.geekmarket.repositories.RoleRepository;
import com.geekbrains.geekmarket.repositories.UserRepository;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private static final String QUEUE_NAME = "auth_err";

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Autowired
	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		return userRepository.findOneByUserName(userName);
	}


	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findOneByUserName(userName);
		if (user == null) {
			{
				ConnectionFactory connectionFactory = new ConnectionFactory();
				connectionFactory.setHost("localhost");
				try(Connection connection = connectionFactory.newConnection()) {
					try {
						try(Channel channel = connection.createChannel()){
							channel.queueDeclare(QUEUE_NAME,false,false,false, null);

							channel.basicPublish("",QUEUE_NAME,null, userName.getBytes());
							System.out.println("sent msg: "+userName);
						}
					} catch (IOException e) {
						e.printStackTrace();
					} catch (TimeoutException e) {
						e.printStackTrace();
					}
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			throw new UsernameNotFoundException("Не правильно набран пароль.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void save(SystemUser systemUser) {
		//перекладываем при создании пользователя
		User user = new User();
		user.setUserName(systemUser.getUserName());
		user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
		user.setFirstName(systemUser.getFirstName());
		user.setLastName(systemUser.getLastName());
		user.setEmail(systemUser.getEmail());
		user.setPhone(systemUser.getPhone());

		user.setRoles(Arrays.asList(roleRepository.findOneByName("ROLE_USER")));

		//стандартный метод не требуется определять
		userRepository.save(user);
	}

}
