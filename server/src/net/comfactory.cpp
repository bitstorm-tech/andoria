/*
 * ComFactory.cpp
 *
 *  Created on: 07.12.2012
 *      Author: SG
 */
// std
#include <vector>
// boost
#include <boost/shared_ptr.hpp>
// net
#include "../global.hpp"
#include "com.hpp"
#include "coms.hpp"
#include "comfactory.hpp"

using namespace boost;

using namespace andoria;
using namespace andoria::net;

boost::shared_ptr<ICom> ComFactory::GetCom(unsigned char cmd,
    const std::vector<unsigned char>& data) const
{
  // create command
  ICom* com = nullptr;
  switch(cmd)
  {
    case 0:
      com = new PingCom();
      break;
    case 1:
      com = new TimeSynCom();
      break;
  }
  boost::shared_ptr<ICom> result(com);
  result->Init(data);
  return result;
}

