package com.example.common.mapper

interface Mapper<F, T> {
    fun toModel (value: F): T

    fun fromModel (value: T): F
}