/*
 * ComFactory.h
 *
 *  Created on: 07.12.2012
 *      Author: SG
 */

#ifndef COMFACTORY_H_
#define COMFACTORY_H_

namespace andoria
{
  namespace net
  {
    class ICom;

    class ComFactory
    {
      public:
        boost::shared_ptr<andoria::net::ICom> GetCom(unsigned char cmd,
            const std::vector<unsigned char>& data) const;
    };
  };
}; /* namespace net */
#endif /* COMFACTORY_H_ */
