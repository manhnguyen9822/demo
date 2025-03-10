/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.haui_megatech.service;

import com.haui_megatech.model.*;
import java.util.*;
import com.haui_megatech.dto.*;

/**
 *
 * @author manh
 */
public interface UserService {
    CommonResponseDTO<List<User>> getList();
    CommonResponseDTO<List<User>> searchList(String keyword);
    CommonResponseDTO addOne(User user);
    CommonResponseDTO addList(ArrayList<User> users);
    CommonResponseDTO deleteOne(Integer id);
    Optional<User> findByUsername(String username);
    Optional<User> findById(Integer id);
    CommonResponseDTO updateOne(Integer id, UserDTO user);
}
