package com.demo.user.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.demo.user.repository.AllUsersDataFetures;
import com.demo.user.repository.UserDataFetures;
import com.demo.user.repository.UserRepository;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class UsersGraphService {

	@Value("classpath:users.graphql")
	private Resource resource;
	
	private GraphQL graphQL;
	
	@Autowired
	private AllUsersDataFetures allUsersDataFetures;
	
	@Autowired
	private UserDataFetures userDataFetures;
	
	@Autowired
	private UserRepository userRepository;
	
	 @Autowired
	    public UsersGraphService(UserRepository userRepository, AllUsersDataFetures allUsersDataFetures,
	    		UserDataFetures userDataFetures) {
	        this.userRepository=userRepository;
	        this.allUsersDataFetures=allUsersDataFetures;
	        this.userDataFetures=userDataFetures;
	    }
	
	@PostConstruct
	private void LoadSchema() throws IOException {
		System.out.println("1");
		File schemaFile=resource.getFile();
		System.out.println("2");
		TypeDefinitionRegistry typeRegistry=new SchemaParser().parse(schemaFile);
		System.out.println("3");
		RuntimeWiring runtimeWiring=this.buildRuntimeWiring();
		System.out.println("4");
		GraphQLSchema schema=new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);
		System.out.println("5");
		graphQL = GraphQL.newGraphQL(schema).build();
		System.out.println("6");
	}
	
//	private void loadAllInHSql(Users user) {
//		userRepository.save(user);
//	}
	
	private RuntimeWiring buildRuntimeWiring() {
		System.out.println("runtime");
		return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring
				.dataFetcher("allUsers", allUsersDataFetures)
				.dataFetcher("users", userDataFetures)).build();
		
	}
	
	public GraphQL graphQL(){
		System.out.println("Inside graphql");
		return graphQL;
	}
}
