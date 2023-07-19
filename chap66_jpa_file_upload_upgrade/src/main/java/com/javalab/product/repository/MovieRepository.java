package com.javalab.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javalab.product.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {


//    @Query("select m, avg(coalesce(r.grade,0)),  count(r) from Movie m " +
//            "left outer join Review  r on r.movie = m group by m")
//    Page<Object[]> getListPage(Pageable pageable);

    @Query("select m, mi, avg(coalesce(r.grade,0)),  count(r) from Movie m " +
            "left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review  r on r.movie = m group by m ")
    Page<Object[]> getListPage(Pageable pageable);


    /**
     * [조회 결과]
     *  - List<Object[]> : 객체 배열을 요소로 가지는 List
     *  - 쿼리 결과 구성도는 '수업내용' 게시판 참조
     * @return
     */
    @Query("select m, mi ,avg(coalesce(r.grade,0)), count(r)" +
            " from Movie m left outer join MovieImage mi on mi.movie = m " +
            " left outer join Review  r on r.movie = m "+
            " where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(@Param("mno") Long mno);
}
