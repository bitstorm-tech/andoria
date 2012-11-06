/**
 * andoria server application main entry point
 * @file main.cpp
 * @author Sandro Guldner
 * @version 1.0
 * @brief 6. November 2012
 */
#include "server/server.hpp"
#include "andoriaserver.hpp"
#include "configuration.hpp"

/**
 * starts the andoria server.
 * @param length command line parameter length.
 * @param data command line arguments.
 */
int main(int length, char** data)
{
	// create configuration
	Configuration* pCfg = Configuration::GetInstance();
	pCfg->Init();
	// create server
	AndoriaServer server;
	int result = server.Exec();
	pCfg->Delete();
	return result;
}


