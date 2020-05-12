package com.Feelfree2code.STA.service;

import com.Feelfree2code.STA.model.viewModel.UserAddVM;
import com.Feelfree2code.STA.model.viewModel.UserUpdateVM;
import com.Feelfree2code.STA.model.viewModel.UserVM;

import java.util.List;

public interface IUserService {
    List<UserVM> get(boolean showIsDeleted);

    UserVM getById(Integer id);

    boolean add(UserAddVM model);

    boolean update(Integer id, UserUpdateVM model);

    boolean delete(Integer id);
}
