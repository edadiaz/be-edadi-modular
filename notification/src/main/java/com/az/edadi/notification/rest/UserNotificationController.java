package com.az.edadi.notification.rest;

import com.az.edadi.common.constant.SortDirection;
import com.az.edadi.common.response.SuccessResponse;
import com.az.edadi.model.response.notification.NotificationResponse;
import com.az.edadi.notification.service.UserNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class UserNotificationController {

    private final UserNotificationService userNotificationService;

    @GetMapping("/me")
    SuccessResponse<Page<NotificationResponse>> getUserNotifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "create_ts") String sortBy,
            @RequestParam(defaultValue = "DESC") SortDirection sortDirection
    ) {

        var pageable = PageRequest.of(page, size,
                Sort.by(Sort.Direction.fromString(sortDirection.toString()), sortBy));
        var result = userNotificationService.getUserNotifications(pageable);
        return SuccessResponse.fetch(result);

    }
}
