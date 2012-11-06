/**
 * main program file.
 * @file andoriaserver.hpp
 * @author Sandro Guldner
 * @version 1.0
 * @brief 6. November 2012
 */

#ifndef ANDORIASERVER_HPP_
#define ANDORIASERVER_HPP_

class Configuration;
namespace server
{
	class Server;
};

/**
 * main program class. contains the main loop.
 */
class AndoriaServer
{
	public:
		AndoriaServer();
		~AndoriaServer();

		int Exec();

		void Exit();
		void Exit(int param);

	protected:
		Configuration* m_PCfg;
		bool m_Loop;
		int m_Exit;

		// network
		server::Server* m_PServer;
};

#endif /* ANDORIASERVER_HPP_ */
