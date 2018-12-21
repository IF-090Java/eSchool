package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.MarkTypeDTO;
import academy.softserve.eschool.model.MarkType;
import academy.softserve.eschool.repository.MarkTypeRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static academy.softserve.eschool.auxiliary.Utility.transform;

@Service
@RequiredArgsConstructor
public class MarkTypeService {

    @NonNull
    private MarkTypeRepository markTypeRepository;

    public List<MarkTypeDTO> getAll() {
        return markTypeRepository.findAll().stream().map(i -> transform(i))
                    .collect(Collectors.toCollection(ArrayList::new));
    }

    public MarkTypeDTO getMarkTypeById(int id) {
        return transform(markTypeRepository.getOne(id));
    }

    public MarkTypeDTO addMarkType(MarkTypeDTO markTypeDTO) {
        return transform(markTypeRepository.save(transform(markTypeDTO)));
    }


    /**
     * Update mark type to passed {@link MarkTypeDTO} if {@link MarkType} with passed ID exist.
     * @param id ID of mark type.
     * @param markTypeDTO data for update.
     * @return updated  {@link MarkTypeDTO}.
     */
    public MarkTypeDTO updateMarkType(int id, MarkTypeDTO markTypeDTO) {
        MarkType markType =  markTypeRepository.getOne(id);
        if (markType != null) {
            markType.setMarkType(markTypeDTO.getMarkType());
            markType.setDescription(markTypeDTO.getDescription());
            markType.setActive(markTypeDTO.isActive());
            markType = markTypeRepository.save(markType);
        }
        return transform(markType);
    }
}
