package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.ClassTeacherSubjectLink;
import academy.softserve.eschool.model.ClassTeacherSubjectLinkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassTeacherSubjectRepository extends JpaRepository<ClassTeacherSubjectLink, ClassTeacherSubjectLinkId> {

}
