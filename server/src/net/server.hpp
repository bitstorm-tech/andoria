/*
 * server.hpp
 *
 *  Created on: 12.11.2012
 *      Author: SG
 */

#ifndef SERVER_HPP_
#define SERVER_HPP_

class Configuration;

namespace andoria
{
  namespace net
  {
    class Server
    {
    public:
      static const int SERVER_NETWORK_PORT = 80;

      static const unsigned char CMS_SERVER = 0;
      static const unsigned char CMS_DATA = 1;

      static const unsigned char CMS_GAME = 2;
      static const unsigned char FNC_GAME_TIME_SYNC = 0;
      static const unsigned char FNC_GAME_POISITION_UPDATE = 1;


      static const unsigned char CMS_CHAT = 3;


      Server(boost::asio::io_service& io_service);
      ~Server();

    protected:
      boost::asio::ip::tcp::acceptor m_Acceptor;

      void StartAccept();
      void HandleAccept(boost::shared_ptr<net::TcpConnection> new_connection,
            const boost::system::error_code& error);

      andoria::Configuration* m_PCfg;
    };
  };
}; /* namespace net */
#endif /* SERVER_HPP_ */

