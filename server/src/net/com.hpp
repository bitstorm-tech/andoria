/*
 * Com.hpp
 *
 *  Created on: 07.12.2012
 *      Author: SG
 */
#ifndef COM_HPP_
#define COM_HPP_

// boost
#include <vector>

namespace andoria
{
  namespace net
  {
    class ICom
    {
      public:
        ICom();
        virtual ~ICom();

        // inits the object and sets the received data package
        virtual andoria::Error Init(const std::vector<unsigned char>& data) = 0;
        // executes the network command
        virtual andoria::Error Exec() = 0;
        // prepares and returns the result for the client
        virtual andoria::Error Result() = 0;

      protected:

    };
  }; /* namespace net */
};
#endif /* COM_HPP_ */
