package com.example.note

interface Communicator {
    fun passData(header: String?, content: String?, time: String?)
}