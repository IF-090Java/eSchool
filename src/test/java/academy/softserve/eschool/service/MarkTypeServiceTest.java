package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.MarkTypeDTO;
import academy.softserve.eschool.model.MarkType;
import academy.softserve.eschool.repository.MarkTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static academy.softserve.eschool.auxiliary.Utility.transform;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MarkTypeServiceTest {
    @InjectMocks
    private MarkTypeService markTypeService;

    @Mock
    private MarkTypeRepository markTypeRepository;

    private List<MarkTypeDTO> markTypeDTOS = new ArrayList<>();

    private List<MarkType> markTypes = new ArrayList<>();

    private MarkTypeDTO edited;
    @Before
    public void setUp() {
        markTypeDTOS.add(MarkTypeDTO.builder().id(1).isActive(true).markType("Модуль").build());
        markTypeDTOS.add(MarkTypeDTO.builder().id(2).isActive(true).markType("Контрольна").build());
        markTypeDTOS.add(MarkTypeDTO.builder().id(3).isActive(true).markType("Самостійна").build());

        markTypes.add(MarkType.builder().id(1).isActive(true).markType("Модуль").build());
        markTypes.add(MarkType.builder().id(2).isActive(true).markType("Контрольна").build());
        markTypes.add(MarkType.builder().id(3).isActive(true).markType("Самостійна").build());

        edited = MarkTypeDTO.builder().isActive(false).id(1).markType("Лабораторна").description("test").build();
    }

    @Test
    public void getAll() {
        Mockito.when(markTypeRepository.findAll()).thenReturn(markTypes);
        assertEquals(markTypeDTOS, markTypeService.getAll());
    }

    @Test
    public void getMarkTypeById() {
        Mockito.when(markTypeRepository.getOne(2)).thenReturn(markTypes.get(1));
        assertEquals(markTypeDTOS.get(1), markTypeService.getMarkTypeById(2));
    }

    @Test
    public void addMarkType() {
        Mockito.when(markTypeRepository.save(transform(markTypeDTOS.get(0)))).thenReturn(markTypes.get(0));

        assertEquals(markTypeDTOS.get(0), markTypeService.addMarkType(markTypeDTOS.get(0)));
    }

    @Test
    public void updateMarkType() {
        Mockito.when(markTypeRepository.getOne(1)).thenReturn(markTypes.get(0));
        MarkType markType = MarkType.builder().id(edited.getId()).isActive(edited.isActive()).markType(edited.getMarkType()).description(edited.getDescription()).build();

        Mockito.when(markTypeRepository.save(markType)).thenReturn(markType);
        assertEquals(edited, markTypeService.updateMarkType(1, edited));
    }
}