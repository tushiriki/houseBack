package com.house.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.house.convertDto.HistoriqueConvert; 
import com.house.dto.HistoriqueDto; 
import com.house.entity.HistoriqueEntity; 
import com.house.helper.PagingAndSortingHelper;
import com.house.repository.HistoriqueRepository;
 
@Service
public class HistoriqueService {
  @Autowired
private HistoriqueRepository repository;

public Map<String, Object> getHistorique(Integer idMenage, Integer idUSer, Integer idExercice, Integer idTrimestre,
        int page, int size, String[] sort) {
   Pageable pagingSort = PagingAndSortingHelper.pagination(sort, page, size);
        Page<HistoriqueEntity> pg = null;    

        if (idMenage!=null && idExercice!=null && idTrimestre!=null && idUSer!=null) {
			pg = repository.findByIdMenageAndIdExerciceAndIdTrimestreAndIdUser(idMenage,idExercice,idTrimestre,idUSer,pagingSort);
		}else if (idMenage==null && idExercice!=null && idTrimestre!=null && idUSer!=null) {
			pg = repository.findByIdExerciceAndIdTrimestreAndIdUser(idExercice,idTrimestre,idUSer,pagingSort);
		}else if (idMenage==null && idExercice==null && idTrimestre==null && idUSer!=null) {
			pg = repository.findByIdUser(idUSer,pagingSort);
		}else if (idMenage==null && idExercice!=null && idTrimestre!=null && idUSer==null) {
			pg = repository.findByIdExerciceAndIdTrimestre(idExercice,idTrimestre,pagingSort);
		}else if (idMenage!=null && idExercice!=null && idTrimestre!=null && idUSer==null) {
			pg = repository.findByIdMenageAndIdExerciceAndIdTrimestre(idMenage,idExercice,idTrimestre,pagingSort);
		}else if (idMenage!=null && idExercice==null && idTrimestre==null && idUSer==null) {
			pg = repository.findByIdMenage(idMenage,pagingSort);
		}else if (idMenage==null && idExercice!=null && idTrimestre==null && idUSer==null) {
			pg = repository.findByIdExercice(idExercice,pagingSort);
		}else{
            pg = repository.findByIdMenage(null,pagingSort);
        }

        List<HistoriqueDto> list = new ArrayList<>(); 
        List<HistoriqueEntity> dataEntity = pg.getContent();
        for (HistoriqueEntity historiqueEntity : dataEntity) {
            HistoriqueDto historiqueDto = HistoriqueConvert.getInstance().toDto(historiqueEntity);
            historiqueDto.getUser().setPassword(null);

            list.add(historiqueDto);
        } 
      Map<String, Object> dtos = PagingAndSortingHelper.filteredAndSortedResult(pg.getNumber(), pg.getSize(), pg.getTotalPages(),
        list);

     return dtos;
}

public boolean delete(Long id) {
    try {
        repository.deleteById(id);
          return true;
    } catch (Exception e) {
          return false;
    }
}

public boolean deleteAll() {
       try {
        repository.deleteAll();
          return true;
    } catch (Exception e) {
          return false;
    }
}

public List<HistoriqueDto> getAlls() {
  List<HistoriqueEntity> histo= repository.findAll();
  List<HistoriqueDto> list= new ArrayList<>();

     try {
        for (HistoriqueEntity historiqueEn : histo) {
          System.out.println("list  : ****************************"+historiqueEn.getIdExercice());
            HistoriqueDto historiqueDto = HistoriqueConvert.getInstance().toDto(historiqueEn);
            historiqueDto.getUser().setPassword(null);

            list.add(historiqueDto);
        } 
     } catch (Exception e) {
      list=Collections.emptyList();
     }
    return list;
} 
}
