package com.kagankuscu.webchat.service;

import com.kagankuscu.webchat.common.AddVM;
import com.kagankuscu.webchat.common.BaseDTO;
import com.kagankuscu.webchat.common.BaseVM;
import com.kagankuscu.webchat.common.UpdateVM;
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
