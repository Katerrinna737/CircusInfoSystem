package circus.kate.com.circusinfosystem.repository;

import circus.kate.com.circusinfosystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); //проверка существования пользователя для авторизации
}

