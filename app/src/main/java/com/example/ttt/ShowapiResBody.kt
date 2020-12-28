package com.example.ttt

data class ShowapiResBody(
    val `data`: List<Data>,
    val dataSize: Int,
    val expSpellName: String,
    val expTextName: String,
    val fee_num: Int,
    val flag: Boolean,
    val logo: String,
    val mailNo: String,
    val msg: String,
    val possibleExpList: List<Any>,
    val queryTimes: Int,
    val ret_code: Int,
    val status: Int,
    val tel: String,
    val update: Long,
    val updateStr: String,
    val upgrade_info: String
)