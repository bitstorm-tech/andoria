project(Andoria_Server)
cmake_minimum_required(VERSION 2.8)

file(GLOB SOURCES src/*.cpp)

add_executable(andoria-server ${SOURCES})
