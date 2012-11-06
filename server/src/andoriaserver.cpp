/**
 * main program file.
 * @file andoriaserver.cpp
 * @author Sandro Guldner
 * @version 1.0
 * @brief 6. November 2012
 */
// network
#include "server.hpp"
// core
#include "configuration.hpp"
#include "andoriaserver.hpp"

using namespace server;

AndoriaServer::AndoriaServer()
: m_PCfg(nullptr), m_Loop(true), m_Exit(0),
  m_PServer(nullptr)
{
	// configuration
	m_PCfg = Configuration::GetInstance();
	// network server
	m_PServer = new Server();
}

AndoriaServer::~AndoriaServer()
{
	delete m_PServer;
	m_PServer = nullptr;
}

int AndoriaServer::Exec()
{
	while(m_Loop)
	{

	}
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
