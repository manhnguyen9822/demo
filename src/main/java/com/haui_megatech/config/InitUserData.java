/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.haui_megatech.config;

import com.haui_megatech.*;
import com.haui_megatech.model.*;
import com.haui_megatech.repository.*;
import com.haui_megatech.repository.impl.*;
import java.util.*;

/**
 *
 * @author duytran
 */
public class InitUserData {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl(new ApplicationContext());
        userRepository.saveAll(new ArrayList<>(List.of(
                User.builder()
                        .username("admin@gmail.com")
                        .password("12345678Abc!")
                        .firstName("Duy")
                        .lastName("Trần Văn")
                        .phoneNumber("0336118268")
                        .email("tranduy@gmail.com")
                        .build()
//                User.builder()
//                        .username("quocuy")
//                        .password("12345678Abc!")
//                        .firstName("uy")
//                        .lastName("Dang Quoc Uy")
//                        .phoneNumber("0336118269")
//                        .email("dquy@gmail.com")
//                        .build()
//                User.builder()
//                        .username("manh")
//                        .password("123")
//                        .firstName("Mạnh")
//                        .lastName("Nguyễn Văn Mạnh")
//                        .phoneNumber("0336118270")
//                        .email("nguyenmanh@gmail.com")
//                        .build(),
        )));

    }
}
