package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.PasswordResetRequest;

public interface PasswordResetService {

    void updatePassword(PasswordResetRequest passwordResetRequest);
}
