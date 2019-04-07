package com.abd.elastic.repository;

import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import com.abd.elastic.entity.User;

/**
 *
 * @author : aaabdullah
 * @date : Apr 2, 2019
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Override
	public User getUserById(String userId) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(QueryBuilders.matchQuery("userid", userId))
				.build();
		List<User> users = esTemplate.queryForList(searchQuery, User.class);
		if (!users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public User searchBy(String field, String value) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.matchQuery(field, value).fuzziness(Fuzziness.ONE).prefixLength(3)).build();
		List<User> users = esTemplate.queryForList(searchQuery, User.class);
		if (!users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public List<User> getAllBy(String value) {
		QueryBuilder query = QueryBuilders.boolQuery()
				.should(
						QueryBuilders.queryStringQuery(value)
						.lenient(true)
						.field("name")
						.field("profession")
						)
				.should(QueryBuilders.queryStringQuery("*" + value + "*")
						.lenient(true)
						.field("name")
						.field("profession")
						);
		SearchQuery rootQuery = new NativeSearchQueryBuilder().withQuery(query).build();
		return esTemplate.queryForList(rootQuery, User.class);
	}

}
