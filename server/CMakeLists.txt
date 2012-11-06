project(Andoria_Server)
cmake_minimum_required(VERSION 2.8)

SET(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}/target) 
SET(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}/target)
SET(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}/target)

add_subdirectory(src)

file(GLOB SOURCES *.cpp server/*.cpp)

add_executable(andoria-server ${SOURCES})
