/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.j148.j148libraryfrontend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author glenl
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Fines {
    private long fineId;
    private double amount;
    private boolean paid;
    private BookBorrowed bookBorrowed;
}
