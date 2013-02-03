/*
 * coms.cpp
 *
 *  Created on: 07.12.2012
 *      Author: SG
 */
#include "../global.hpp"
#include "com.hpp"
#include "coms.hpp"
// std
#include <vector>

using namespace andoria;
using namespace andoria::net;

//******************************************************
// 00 - "ping" command
//******************************************************
PingCom::PingCom()
{
}

PingCom::~PingCom()
{}

Error PingCom::Init(const std::vector<unsigned char>& data)
{
  return Error::ERR_OK;
}

andoria::Error PingCom::Exec()
{
  return Error::ERR_OK;
}

Error PingCom::Result()
{
  return Error::ERR_OK;
}


//******************************************************
// 01 - time synchronisation command
//******************************************************
TimeSynCom::TimeSynCom()
{}

TimeSynCom::~TimeSynCom()
{}

Error TimeSynCom::Init(const std::vector<unsigned char>& data)
{
  return Error::ERR_OK;
}

Error TimeSynCom::Exec()
{
  return Error::ERR_OK;
}

Error TimeSynCom::Result()
{
  return Error::ERR_OK;
}
