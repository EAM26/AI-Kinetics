package com.emcode.aikinetics.sporttype;

import com.emcode.aikinetics.account.Account;
import com.emcode.aikinetics.sporttype.dto.SportTypeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SportTypeServiceTest {




    @Mock
    SportTypeRepository sportTypeRepository;

    @Spy
    SportTypeMapper sportTypeMapper;

    @InjectMocks
    SportTypeService sportTypeService;


    @Test
    void shouldCreateSportTypeAndReturnUriAndObject() {

    }

    @Test
    void shouldReturnSingleSportTypeById() {
        // Arrange
        var fakeAccount = new Account(1L, "John", "john@mail.nl", null);
        var fakeSportType = new SportType(5L, "Running", fakeAccount);
        var expected = SportTypeResponse.builder()
                .id(5L)
                .keyName("Running")
                .accountId(1L)
                .build();
        when(sportTypeRepository.findById(5L)).thenReturn(Optional.of(fakeSportType));

        // Act
        SportTypeResponse actual = sportTypeService.getSingleSportTypeById(5L);

        // Assert
        verify(sportTypeRepository).findById(5L);
        verifyNoMoreInteractions(sportTypeRepository);

        assertEquals(expected.id(), actual.id());
        assertEquals(expected.keyName(), actual.keyName());
        assertEquals(expected.accountId(), actual.accountId());
    }

    @Test
    void getAllSportTypes() {
    }

    @Test
    void getAllSportTypesByAccountId() {
    }
}