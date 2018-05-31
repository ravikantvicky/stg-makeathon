package com.stg.makeathon.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stg.makeathon.domain.Events;

@Repository
public interface EventsRepository extends JpaRepository<Events, Integer> {
	public List<Events> findByEventDateGreaterThanAndEventTimeGreaterThanAndKeywordsIgnoreCaseContaining(
			Date currentDate, Time currentTime, String keyword);

	@Query("select e from Events e where STR_TO_DATE(concat(e.eventDate, ' ', e.eventTime), '%Y-%m-%d %H:%i:%s') > current_timestamp() and (upper(e.keywords) like upper(?1)) and upper(location) = upper(?2)")
	public List<Events> findByKeywordAndLocation(String keyword, String location);

	@Query("select e from Events e where STR_TO_DATE(concat(e.eventDate, ' ', e.eventTime), '%Y-%m-%d %H:%i:%s') > current_timestamp() and (upper(e.keywords) like upper(?1))")
	public List<Events> findByKeyword(String keyword);

	@Query(value = "SELECT * FROM `events` e WHERE STR_TO_DATE(concat(e.event_date, ' ', e.event_time), '%Y-%m-%d %H:%i:%s')>current_timestamp ORDER BY STR_TO_DATE(concat(e.event_date, ' ', e.event_time), '%Y-%m-%d %H:%i:%s') LIMIT 0,40", nativeQuery = true)
	public List<Events> findUpcomingEvents();
}
