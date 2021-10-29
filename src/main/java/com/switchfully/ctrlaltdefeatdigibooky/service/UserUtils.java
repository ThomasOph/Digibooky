package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.model.UserRole;

// CODEREVIEW this interface serves no purpose
public interface UserUtils {
	boolean isUUIDUserRole(String uuid, UserRole role);
}
