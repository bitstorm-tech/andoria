/*
 * tcpconnection.hpp
 *
 *  Created on: 13.11.2012
 *      Author: SG
 */

#ifndef TCPCONNECTION_HPP_
#define TCPCONNECTION_HPP_

namespace andoria
{
  namespace net
  {
    class ComFactory;

    class TcpConnection : public boost::enable_shared_from_this<net::TcpConnection>
    {
    public:

      virtual ~TcpConnection();
      static boost::shared_ptr<net::TcpConnection> Create(boost::asio::io_service& io_service)
      {
        return boost::shared_ptr<net::TcpConnection>(new net::TcpConnection(io_service));
      }

      boost::asio::ip::tcp::socket& GetSocket() {return m_Socket;}
      const boost::asio::ip::tcp::socket& GetSocket() const {return m_Socket;}

      void Start();

    protected:
      // functions
      TcpConnection(boost::asio::io_service& io_service);

      void HandleRead(const boost::system::error_code& error, size_t size);
      void HandleWrite(const boost::system::error_code& code, size_t size);

      //member
      unsigned char m_Buffer[NETWORK_PACKAGE_LENGTH];
      boost::asio::ip::tcp::socket m_Socket;
      andoria::net::ComFactory* m_Factory;

    };
  };
}; /* namespace net */
#endif /* TCPCONNECTION_HPP_ */
