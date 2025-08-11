package com.az.edadi.model.response.common;

import com.az.edadi.model.constant.PageTab;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageTabResponse {
    private String name;
    private PageTab type;
}
