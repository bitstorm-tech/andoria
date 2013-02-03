/**
 * main program file.
 * @file andoriaserver.hpp
 * @author Sandro Guldner
 * @version 1.0
 * @brief 6. November 2012
 */

#ifndef ANDORIASERVER_HPP_
#define ANDORIASERVER_HPP_


namespace boost
{
  namespace asio
  {
    class io_service;
  };
};

namespace andoria
{
  class Configuration;
  namespace net
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

      // network server
      boost::asio::io_service* m_io_service;

      // network
      andoria::net::Server* m_PServer;
  };
};

#endif /* ANDORIASERVER_HPP_ */
