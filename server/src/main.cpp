/**
 * andoria server application main entry point
 * @file main.cpp
 * @author Sandro Guldner
 * @version 1.0
 * @brief 6. November 2012
 */
#include <iostream>

#include "andoriaserver.hpp"
#include "configuration.hpp"

using namespace std;
using namespace andoria;

void PrintMessage()
{
  cout << "*****************************************************" << endl;
  cout << "Andoria Server" << endl;
  cout << "2012" << endl;
  cout << "by" << endl;
  cout << "Josef Bauer" << endl;
  cout << "Sandro Guldner" << endl;
}

void PrintError()
{}

/**
 * starts the andoria server.
 * @param length command line parameter length.
 * @param data command line arguments.
 */
int main(int length, char** data)
{
  PrintMessage();
  // create configuration
  Configuration* pCfg = Configuration::GetInstance();
  pCfg->Init();
  // create server
  AndoriaServer server;
  int result = server.Exec();
  pCfg->Delete();
  return result;
}


