package com.localbasket.service;

import java.util.List;

import com.localbasket.dto.NotificationRequestDTO;
import com.localbasket.dto.NotificationResponseDTO;

public interface NotificationService {

    NotificationResponseDTO createNotification(NotificationRequestDTO request);

    NotificationResponseDTO getNotificationById(Long id);

    List<NotificationResponseDTO> getAllNotifications();

    List<NotificationResponseDTO> getNotificationsByUserId(Long userId);

    NotificationResponseDTO markAsRead(Long id);

    void deleteNotification(Long id);
}