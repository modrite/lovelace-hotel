package com.group3.lovelacehotel.search;

import com.group3.lovelacehotel.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;

    public Optional<Room> searchAvailableRoom(LocalDateTime checkInDate,
                                              LocalDateTime expectedCheckOutDate,
                                              Long numberOfAdults,
                                              Long numberOfChildren) {
        return searchRepository.searchAvailableRoom(checkInDate, expectedCheckOutDate, numberOfAdults, numberOfChildren);
    }

}
