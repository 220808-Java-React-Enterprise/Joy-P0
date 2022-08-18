drop table if exists reviews cascade;
drop table if exists users cascade;
drop table if exists restaurants cascade;


create table users (
	id varchar not null,
	username varchar not null,
	password varchar not null,
	role varchar not null,
	
	constraint pk_users_id
		primary key (id)
);

create table restaurants (
	id varchar not null,
	name varchar not null,
	street varchar not null,
	city varchar not null,
	state varchar not null,
	zipcode varchar not null,
	
	constraint pk_restaurants_id
		primary key (id)
);

create table reviews (
	id varchar not null,
	comment varchar not null,
	rating int not null,
	user_id varchar not null,
	restaurant_id varchar not null,
	
	constraint pk_reviews_id
		primary key (id),
		
	constraint fk_user_id
		foreign key (user_id) references users (id),
		
	constraint fk_restaurant_id
		foreign key (restaurant_id) references restaurants (id)
);


