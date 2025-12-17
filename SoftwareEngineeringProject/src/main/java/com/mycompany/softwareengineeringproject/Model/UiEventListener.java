/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

/**
 *
 * @author matda
 */
//Observer Interface, define what can be observed
public interface UiEventListener {
    void onShowNotification(String title, String message);
    void onShowWarning(String title, String header, String message);
    void onShowError(String title, String header, String message);
    void onShowAudioPlayer(String filename);
}
