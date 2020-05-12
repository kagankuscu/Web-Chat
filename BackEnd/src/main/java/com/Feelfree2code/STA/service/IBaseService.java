package com.Feelfree2code.STA.service;

import com.Feelfree2code.STA.common.AddVM;
import com.Feelfree2code.STA.common.BaseDTO;
import com.Feelfree2code.STA.common.BaseVM;
import com.Feelfree2code.STA.common.UpdateVM;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

@NoRepositoryBean
public interface IBaseService<V extends BaseVM, S extends AddVM, U extends UpdateVM, D extends BaseDTO> {

    ResponseEntity<List<V>> get();

    ResponseEntity<V> getById(Integer id);

    ResponseEntity<D> add(S model);

    ResponseEntity<D> update(Integer id, U model);

    ResponseEntity<V> delete(Integer id);

    ResponseEntity<List<V>> getIsDeleted(boolean showIsDeleted);
}
