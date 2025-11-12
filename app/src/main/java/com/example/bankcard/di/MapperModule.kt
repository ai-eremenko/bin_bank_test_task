package com.example.bankcard.di

import com.example.bankcard.data.mapper.BinInfoMapper
import org.koin.dsl.module

val mapperModule = module {
    single { BinInfoMapper }
}