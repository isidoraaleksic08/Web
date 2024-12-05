package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.Event;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    public List<Category> findAll() {
//        return DataHolder.categories.stream().toList();
//    }
//    public Optional<Category> findById(Long id){
//        return DataHolder.categories.stream().filter(category -> category.getId().equals(id)).findFirst();
//    }
}