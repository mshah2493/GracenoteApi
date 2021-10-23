package com.gracenote.api.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.gracenote.api.datafetchers.MovieDataFetcher;
import com.gracenote.api.datafetchers.MovieRecordsDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;

/**
 * @author meets
 *
 */
@Service
public class GraphQLService {
	
	@Value("classpath:schema/*.graphql")
	private Resource[] resources;
	
	@Autowired
	private MovieRecordsDataFetcher movieRecordsDataFetcher;
	
	@Autowired
	private MovieDataFetcher movieDataFetcher;
	
	private GraphQL graphQL;
	
	
	/**
	 * This class is where all graphql-java query execution begins. It combines the objects that are neededto make a successful graphql query.
	 * @return GraphQL
	 */
	public GraphQL getGraph() {
		return graphQL;
	}
	
	/**
	 * Reads the GraphQL schema files to build a GraphQL object
	 * @throws Exception
	 */
	@PostConstruct
	public void init() throws Exception {
		 final List<File> schemas = Arrays.stream(resources).filter(Resource::isFile).map(resource -> {
			 try {
				 return resource.getFile();
			 } catch (IOException e) {
				 throw new RuntimeException("Unable to load schema file.");
			 }
		 }).collect(Collectors.toList());
		 
		 final GraphQLSchema graphQLSchema = buildSchema(schemas);
		 
		 this.graphQL = GraphQL
	                .newGraphQL(graphQLSchema)
	                .build();
	}
	
	/**
	 * @param schemas - list of GraphQL schema  files 
	 * @return GraphQLSchema
	 * @throws Exception
	 */
	private GraphQLSchema buildSchema(final List<File> schemas) throws Exception {
        final SchemaParser schemaParser = new SchemaParser();
        final SchemaGenerator schemaGenerator = new SchemaGenerator();
        final TypeDefinitionRegistry typeDefinitionRegistry = new TypeDefinitionRegistry();

        for (final File schema:schemas) {
            typeDefinitionRegistry.merge(schemaParser.parse(schema));
        }

        final RuntimeWiring runtimeWiring = buildRuntimeWiring();
        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    }
	
	/**
	 * Binds the queries/mutations to methods from data fetcher
	 * @return RuntimeWiring
	 * @throws Exception
	 */
	private RuntimeWiring buildRuntimeWiring() throws Exception {
		return RuntimeWiring.newRuntimeWiring()
		                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getAllMovieRecords", movieRecordsDataFetcher.getAllMovieRecords()))
		                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getAllMovieShows", movieDataFetcher.getAllMovieShows()))
		                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getAllShows", movieDataFetcher.getAllShows()))
		                .build();
		}
}