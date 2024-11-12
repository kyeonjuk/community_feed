package com.kyeonjuk.common.idempotency;

import com.kyeonjuk.common.ui.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Idempotency {

    private final String key;
    private final Response<?> response;
}
