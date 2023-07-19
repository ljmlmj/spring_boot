package com.javalab.product.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javalab.product.dto.MovieDTO;
import com.javalab.product.dto.PageRequestDTO;
import com.javalab.product.dto.PageResultDTO;
import com.javalab.product.entity.Movie;
import com.javalab.product.entity.MovieImage;
import com.javalab.product.repository.MovieImageRepository;
import com.javalab.product.repository.MovieRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository; //final

    private final MovieImageRepository imageRepository; //final

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {

        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);

        movieImageList.forEach(movieImage -> {
            imageRepository.save(movieImage);
        });

        return movie.getMno();
    }

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);

        log.info("==============================================");
        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });


        Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO(
                (Movie)arr[0] ,
                (List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),
                (Double) arr[2],
                (Long)arr[3])
        );

        return new PageResultDTO<>(result, fn);
    }

    /**
     * 
     * 반환 결과에 대한 개념적인 설명은 수업내용 게시판의 이미지 참조 
     * 
     */
    @Override
    public MovieDTO getMovie(Long mno) {
    	
    	// 영화 + 영화이미지 + 평점 + 리뷰수
    	log.info("getMovie 한 영화에 대한 모든 정보 조회 - 1");
        List<Object[]> result = movieRepository.getMovieWithAll(mno);
        
        // 영화에 대한 정보만 추출, 어차피 List의 모든 행은 같은 영화 객체
        log.info("getMovie 영화에 대한 정보만 추출 - 2");
        Movie movie = (Movie) result.get(0)[0];

        log.info("getMovie 영화 이미지 객체 저장용 List 생성 - 3");
        List<MovieImage> movieImageList = new ArrayList<>();

        // List의 모든 행의 arr[1] 에는 영화 이미지 객체 저장되어 있음.
        log.info("getMovie 영화 이미지 객체만 추출 - 4");        
        result.forEach(arr -> {
            MovieImage  movieImage = (MovieImage)arr[1];
            movieImageList.add(movieImage);
        });

        log.info("getMovie 평점과 리뷰수 기본 자료형 값 추출 - 5");
        Double avg = (Double) result.get(0)[2];
        Long reviewCnt = (Long) result.get(0)[3];

        //MovieDTO md = entitiesToDTO(movie, movieImageList, avg, reviewCnt); 
        
        return entitiesToDTO(movie, movieImageList, avg, reviewCnt);
    }

}