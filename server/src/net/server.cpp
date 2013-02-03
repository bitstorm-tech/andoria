/*
 * server.cpp
 *
 *  Created on: 12.11.2012
 *      Author: SG
 */
#include "../global.hpp"
// boost
#include <boost/asio.hpp>
#include <boost/bind.hpp>
#include <boost/shared_ptr.hpp>
#include <boost/enable_shared_from_this.hpp>
#include <boost/asio/io_service.hpp>
// cfg
#include "../configuration.hpp"
// net
#include "tcpconnection.hpp"
#include "server.hpp"

using namespace andoria;
using namespace andoria::net;
using namespace boost::asio::ip;

Server::Server(boost::asio::io_service& io_service)
: m_Acceptor(io_service, tcp::endpoint(tcp::v4(), SERVER_NETWORK_PORT)), m_PCfg(nullptr)
{
  // cfg
  m_PCfg  = Configuration::GetInstance();

  StartAccept();
}

Server::~Server()
{
}

void Server::StartAccept()
{
  boost::shared_ptr<net::TcpConnection> new_connection = TcpConnection::Create(m_Acceptor.get_io_service());
  // events
  m_Acceptor.async_accept(new_connection->GetSocket(),
          boost::bind(&Server::HandleAccept, this, new_connection,
            boost::asio::placeholders::error));
}

void Server::HandleAccept(boost::shared_ptr<net::TcpConnection> new_connection,
          const boost::system::error_code& error)
{
  if(!error)
  {
    new_connection->Start();
  }
  StartAccept();
}

