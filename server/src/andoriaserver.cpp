/**
 * main program file.
 * @file andoriaserver.cpp
 * @author Sandro Guldner
 * @version 1.0
 * @date 6. November 2012
 */
// base
#include "global.hpp"
// boost
#include <boost/asio.hpp>
#include <boost/bind.hpp>
#include <boost/shared_ptr.hpp>
#include <boost/enable_shared_from_this.hpp>
#include <boost/asio/io_service.hpp>
#include "configuration.hpp"
// network
#include "net/tcpconnection.hpp"
#include "net/server.hpp"
// server
#include "andoriaserver.hpp"

using namespace boost::asio;

using namespace andoria;
using namespace andoria::net;

AndoriaServer::AndoriaServer()
: m_PCfg(nullptr), m_Loop(true), m_Exit(0),
  m_io_service(nullptr), m_PServer(nullptr)
{
  // configuration
  m_PCfg = Configuration::GetInstance();
  // service
  m_io_service = new io_service();

  m_PServer = new Server(*m_io_service);
}

AndoriaServer::~AndoriaServer()
{
  delete m_PServer;
  m_PServer = nullptr;
  // service
  delete m_io_service;
  m_io_service = nullptr;
}

int AndoriaServer::Exec()
{
  m_io_service->run();
  return m_Exit;
}

void AndoriaServer::Exit()
{
  Exit(0);
}

void AndoriaServer::Exit(int param)
{
  m_Exit = param;
  m_Loop = false;
}
