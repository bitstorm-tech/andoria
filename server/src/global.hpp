/*
 * global.hpp
 *
 *  Created on: 13.11.2012
 *      Author: SG
 */

#ifndef GLOBAL_HPP_
#define GLOBAL_HPP_

// define windows version
// windows vista 0x0600
// windows 7     0x0601
#define WINVER 0x0600
#define _WIN32_WINNT 0x0600

#include <stddef.h>

namespace andoria
{
  const size_t NETWORK_PACKAGE_LENGTH = 1024;

  enum Error
  {
    ERR_OK = 0,
    ERR_ERROR = -1
  };
};

#endif /* GLOBAL_HPP_ */
