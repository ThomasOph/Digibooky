package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.model.UserRole;

public interface UserUtils {
	boolean isUUIDUserRole(String uuid, UserRole role);
}
