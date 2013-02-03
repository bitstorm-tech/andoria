/**
 * singleton configuration class.
 * @file configuration.hpp
 * @author Sandro Guldner
 * @version 1.0
 * @brief 6. November 2012
 */
#ifndef CONFIGURATION_HPP_
#define CONFIGURATION_HPP_

namespace andoria
{

   /**
    * singleton configuration class.
    */
  class Configuration
  {
    public:
      ~Configuration();
      static Configuration* GetInstance();

      void Init();
      void Delete();

      //*********************************************************
      //* network
      //*********************************************************
      int GetNetworkPort() {return 1000;}
      //std::string GetNetworkIP(){ return "127.0.0.1";}

    protected:
      Configuration();
      static Configuration* m_PCfg;
  };
};
#endif /* CONFIGURATION_HPP_ */
