/*
 * coms.h
 *
 *  Created on: 07.12.2012
 *      Author: SG
 */

#ifndef COMS_H_
#define COMS_H_

namespace andoria
{
  namespace net
  {

    //******************************************************
    // 00 - "ping" command
    //******************************************************
    class PingCom : public ICom
    {
      public:
       PingCom();
       virtual ~PingCom();

       virtual andoria::Error Init(const std::vector<unsigned char>& data);
       virtual andoria::Error Exec();
       virtual andoria::Error Result();
    };

    //******************************************************
    // 01 - time synchronisation command
    //******************************************************
    class TimeSynCom : public ICom
    {
      public:
        TimeSynCom();
        virtual ~TimeSynCom();

        virtual andoria::Error Init(const std::vector<unsigned char>& data);
        virtual andoria::Error Exec();
        virtual andoria::Error Result();

    };
  };
}; /* namespace net */
#endif /* COMS_H_ */
