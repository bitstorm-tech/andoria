/*
 * tcpconnection.cpp
 *
 *  Created on: 13.11.2012
 *      Author: SG
 */
#include "../global.hpp"
// boost
#include <boost/asio.hpp>
#include <boost/bind.hpp>
#include <boost/shared_ptr.hpp>
#include <boost/enable_shared_from_this.hpp>
// net
#include "com.hpp"
#include "coms.hpp"
#include "comfactory.hpp"
#include "tcpconnection.hpp"

using namespace andoria;
using namespace andoria::net;

using namespace boost;
using namespace boost::asio;
using namespace boost::asio::ip;
using namespace boost::asio::placeholders;

TcpConnection::TcpConnection(boost::asio::io_service& io_service)
  : m_Socket(io_service), m_Factory(nullptr)
{
  m_Factory = new ComFactory();
}

TcpConnection::~TcpConnection()
{
  delete m_Factory;
  m_Factory = nullptr;
}

void TcpConnection::Start()
{
  boost::asio::async_read(m_Socket,
          boost::asio::buffer(m_Buffer, NETWORK_PACKAGE_LENGTH),
          boost::bind(
            &TcpConnection::HandleRead, shared_from_this(),
            boost::asio::placeholders::error, boost::asio::placeholders::bytes_transferred));

}

void TcpConnection::HandleRead(const boost::system::error_code& error, size_t size)
{
  if(m_Factory == nullptr) return;
  // finished reading network package
  // check for errors
  if(error.value() == 0 && size > 0)
  {
      // no error occurred
      unsigned char cmd = m_Buffer[0];
      std::vector<unsigned char> data(size);
      for(size_t i = 0; i < size; ++i)
      {
          data[i] = m_Buffer[i];
      }
      // fill the rest of the b
      // get communication object
      shared_ptr<andoria::net::ICom> co = m_Factory->GetCom(cmd, data);

      co->Exec();

  }
}

void TcpConnection::HandleWrite(const boost::system::error_code& error, size_t  size)
{}
