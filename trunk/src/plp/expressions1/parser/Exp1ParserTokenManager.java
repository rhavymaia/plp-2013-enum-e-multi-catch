/* Generated By:JavaCC: Do not edit this line. Exp1ParserTokenManager.java */
package plp.expressions1.parser;
import plp.expressions1.*;
import plp.expressions1.expression.*;
import plp.expressions1.util.*;

/** Token Manager. */
public class Exp1ParserTokenManager implements Exp1ParserConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x10000000000000L) != 0L)
            return 21;
         if ((active0 & 0xfe00L) != 0L)
         {
            jjmatchedKind = 23;
            return 19;
         }
         return -1;
      case 1:
         if ((active0 & 0xfa00L) != 0L)
         {
            jjmatchedKind = 23;
            jjmatchedPos = 1;
            return 19;
         }
         if ((active0 & 0x400L) != 0L)
            return 19;
         return -1;
      case 2:
         if ((active0 & 0xf000L) != 0L)
         {
            jjmatchedKind = 23;
            jjmatchedPos = 2;
            return 19;
         }
         if ((active0 & 0xa00L) != 0L)
            return 19;
         return -1;
      case 3:
         if ((active0 & 0xb000L) != 0L)
         {
            jjmatchedKind = 23;
            jjmatchedPos = 3;
            return 19;
         }
         if ((active0 & 0x4000L) != 0L)
            return 19;
         return -1;
      case 4:
         if ((active0 & 0x1000L) != 0L)
         {
            jjmatchedKind = 23;
            jjmatchedPos = 4;
            return 19;
         }
         if ((active0 & 0xa000L) != 0L)
            return 19;
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 38;
         return jjMoveStringLiteralDfa1_0(0x200000000000L);
      case 37:
         return jjStopAtPos(0, 56);
      case 38:
         jjmatchedKind = 53;
         return jjMoveStringLiteralDfa1_0(0x800000000000L);
      case 40:
         return jjStopAtPos(0, 26);
      case 41:
         return jjStopAtPos(0, 27);
      case 42:
         return jjStopAtPos(0, 51);
      case 43:
         jjmatchedKind = 49;
         return jjMoveStringLiteralDfa1_0(0x1000000000000L);
      case 44:
         return jjStopAtPos(0, 33);
      case 45:
         return jjStopAtPos(0, 50);
      case 46:
         return jjStopAtPos(0, 34);
      case 47:
         return jjStartNfaWithStates_0(0, 52, 21);
      case 58:
         return jjStopAtPos(0, 41);
      case 59:
         return jjStopAtPos(0, 32);
      case 60:
         jjmatchedKind = 37;
         return jjMoveStringLiteralDfa1_0(0x80000000000L);
      case 61:
         jjmatchedKind = 35;
         return jjMoveStringLiteralDfa1_0(0x40000000000L);
      case 62:
         jjmatchedKind = 36;
         return jjMoveStringLiteralDfa1_0(0x100000000000L);
      case 63:
         return jjStopAtPos(0, 40);
      case 91:
         return jjStopAtPos(0, 30);
      case 93:
         return jjStopAtPos(0, 31);
      case 94:
         return jjStopAtPos(0, 55);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x2200L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x1000L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x800L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x400L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 123:
         return jjStopAtPos(0, 28);
      case 124:
         jjmatchedKind = 54;
         return jjMoveStringLiteralDfa1_0(0x400000000000L);
      case 125:
         return jjStopAtPos(0, 29);
      case 126:
         return jjStopAtPos(0, 39);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active0 & 0x800000000000L) != 0L)
            return jjStopAtPos(1, 47);
         break;
      case 43:
         if ((active0 & 0x1000000000000L) != 0L)
            return jjStopAtPos(1, 48);
         break;
      case 61:
         if ((active0 & 0x40000000000L) != 0L)
            return jjStopAtPos(1, 42);
         else if ((active0 & 0x80000000000L) != 0L)
            return jjStopAtPos(1, 43);
         else if ((active0 & 0x100000000000L) != 0L)
            return jjStopAtPos(1, 44);
         else if ((active0 & 0x200000000000L) != 0L)
            return jjStopAtPos(1, 45);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x200L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x800L);
      case 114:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(1, 10, 19);
         return jjMoveStringLiteralDfa2_0(active0, 0x4000L);
      case 115:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000L);
      case 124:
         if ((active0 & 0x400000000000L) != 0L)
            return jjStopAtPos(1, 46);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000L);
      case 100:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(2, 9, 19);
         break;
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000L);
      case 116:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(2, 11, 19);
         break;
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(3, 14, 19);
         break;
      case 103:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000L);
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(4, 15, 19);
         break;
      case 105:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(4, 13, 19);
         break;
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x1000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 104:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(5, 12, 19);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec3 = {
   0x1ff00000fffffffeL, 0xffffffffffffc000L, 0xffffffffL, 0x600000000000000L
};
static final long[] jjbitVec4 = {
   0x0L, 0x0L, 0x0L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec5 = {
   0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec6 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffL, 0x0L
};
static final long[] jjbitVec7 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x0L, 0x0L
};
static final long[] jjbitVec8 = {
   0x3fffffffffffL, 0x0L, 0x0L, 0x0L
};
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 64;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 16)
                        kind = 16;
                     jjCheckNAddStates(0, 4);
                  }
                  else if (curChar == 48)
                  {
                     if (kind > 16)
                        kind = 16;
                     jjCheckNAddStates(5, 11);
                  }
                  else if (curChar == 47)
                     jjAddStates(12, 14);
                  else if (curChar == 36)
                  {
                     if (kind > 23)
                        kind = 23;
                     jjCheckNAdd(19);
                  }
                  else if (curChar == 39)
                     jjAddStates(15, 16);
                  else if (curChar == 34)
                     jjCheckNAddStates(17, 19);
                  break;
               case 21:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(34, 35);
                  else if (curChar == 47)
                     jjCheckNAddStates(20, 22);
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 26;
                  break;
               case 1:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddStates(17, 19);
                  break;
               case 3:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAddStates(17, 19);
                  break;
               case 4:
                  if (curChar == 34 && kind > 21)
                     kind = 21;
                  break;
               case 5:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(23, 26);
                  break;
               case 6:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(17, 19);
                  break;
               case 7:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 8:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(6);
                  break;
               case 9:
                  if (curChar == 39)
                     jjAddStates(15, 16);
                  break;
               case 10:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAdd(11);
                  break;
               case 11:
                  if (curChar == 39 && kind > 22)
                     kind = 22;
                  break;
               case 13:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAdd(11);
                  break;
               case 14:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(15, 11);
                  break;
               case 15:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(11);
                  break;
               case 16:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 17:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(15);
                  break;
               case 18:
                  if (curChar != 36)
                     break;
                  if (kind > 23)
                     kind = 23;
                  jjCheckNAdd(19);
                  break;
               case 19:
                  if ((0x3ff001000000000L & l) == 0L)
                     break;
                  if (kind > 23)
                     kind = 23;
                  jjCheckNAdd(19);
                  break;
               case 20:
                  if (curChar == 47)
                     jjAddStates(12, 14);
                  break;
               case 22:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCheckNAddStates(20, 22);
                  break;
               case 23:
                  if ((0x2400L & l) != 0L && kind > 6)
                     kind = 6;
                  break;
               case 24:
                  if (curChar == 10 && kind > 6)
                     kind = 6;
                  break;
               case 25:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 24;
                  break;
               case 26:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(27, 28);
                  break;
               case 27:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(27, 28);
                  break;
               case 28:
                  if (curChar == 42)
                     jjCheckNAddStates(27, 29);
                  break;
               case 29:
                  if ((0xffff7bffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(30, 28);
                  break;
               case 30:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(30, 28);
                  break;
               case 31:
                  if (curChar == 47 && kind > 7)
                     kind = 7;
                  break;
               case 32:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 26;
                  break;
               case 33:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(34, 35);
                  break;
               case 34:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(34, 35);
                  break;
               case 35:
                  if (curChar == 42)
                     jjCheckNAddStates(30, 32);
                  break;
               case 36:
                  if ((0xffff7bffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(37, 35);
                  break;
               case 37:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(37, 35);
                  break;
               case 38:
                  if (curChar == 47 && kind > 8)
                     kind = 8;
                  break;
               case 39:
                  if ((0x3fe000000000000L & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(0, 4);
                  break;
               case 40:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddTwoStates(40, 41);
                  break;
               case 42:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(33, 35);
                  break;
               case 44:
                  if (curChar == 46)
                     jjstateSet[jjnewStateCnt++] = 45;
                  break;
               case 45:
                  if ((0x3fe000000000000L & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAddTwoStates(46, 47);
                  break;
               case 46:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAddTwoStates(46, 47);
                  break;
               case 48:
                  if (curChar != 48)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(5, 11);
                  break;
               case 50:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddTwoStates(50, 41);
                  break;
               case 51:
                  if ((0xff000000000000L & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddTwoStates(51, 41);
                  break;
               case 53:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjAddStates(36, 38);
                  break;
               case 55:
                  if (curChar == 46)
                     jjstateSet[jjnewStateCnt++] = 56;
                  break;
               case 56:
                  if (curChar == 48)
                     jjstateSet[jjnewStateCnt++] = 57;
                  break;
               case 58:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAddTwoStates(58, 47);
                  break;
               case 59:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(39, 41);
                  break;
               case 61:
                  if (curChar == 46)
                     jjstateSet[jjnewStateCnt++] = 62;
                  break;
               case 62:
                  if (curChar != 48)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAddTwoStates(63, 47);
                  break;
               case 63:
                  if ((0xff000000000000L & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAddTwoStates(63, 47);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 19:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 23)
                     kind = 23;
                  jjCheckNAdd(19);
                  break;
               case 1:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(17, 19);
                  break;
               case 2:
                  if (curChar == 92)
                     jjAddStates(42, 44);
                  break;
               case 3:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAddStates(17, 19);
                  break;
               case 10:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAdd(11);
                  break;
               case 12:
                  if (curChar == 92)
                     jjAddStates(45, 47);
                  break;
               case 13:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAdd(11);
                  break;
               case 22:
                  jjAddStates(20, 22);
                  break;
               case 27:
                  jjCheckNAddTwoStates(27, 28);
                  break;
               case 29:
               case 30:
                  jjCheckNAddTwoStates(30, 28);
                  break;
               case 34:
                  jjCheckNAddTwoStates(34, 35);
                  break;
               case 36:
               case 37:
                  jjCheckNAddTwoStates(37, 35);
                  break;
               case 41:
                  if ((0x100000001000L & l) != 0L && kind > 16)
                     kind = 16;
                  break;
               case 43:
                  if ((0x100000001000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 44;
                  break;
               case 47:
                  if ((0x100000001000L & l) != 0L && kind > 17)
                     kind = 17;
                  break;
               case 49:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(50);
                  break;
               case 50:
                  if ((0x7e0000007eL & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddTwoStates(50, 41);
                  break;
               case 52:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(53);
                  break;
               case 53:
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddStates(36, 38);
                  break;
               case 54:
                  if ((0x100000001000L & l) != 0L)
                     jjCheckNAdd(55);
                  break;
               case 57:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(58);
                  break;
               case 58:
                  if ((0x7e0000007eL & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAddTwoStates(58, 47);
                  break;
               case 60:
                  if ((0x100000001000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 61;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 19:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 23)
                     kind = 23;
                  jjCheckNAdd(19);
                  break;
               case 1:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(17, 19);
                  break;
               case 10:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 22:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(20, 22);
                  break;
               case 27:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(27, 28);
                  break;
               case 29:
               case 30:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(30, 28);
                  break;
               case 34:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(34, 35);
                  break;
               case 36:
               case 37:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(37, 35);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 64 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   40, 41, 42, 43, 44, 49, 51, 41, 52, 59, 60, 61, 21, 32, 33, 10, 
   12, 1, 2, 4, 22, 23, 25, 1, 2, 6, 4, 28, 29, 31, 35, 36, 
   38, 42, 43, 44, 53, 54, 55, 59, 60, 61, 3, 5, 7, 13, 14, 16, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec4[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec5[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec6[i2] & l2) != 0L);
      case 51:
         return ((jjbitVec7[i2] & l2) != 0L);
      case 61:
         return ((jjbitVec8[i2] & l2) != 0L);
      default :
         if ((jjbitVec3[i1] & l1) != 0L)
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, "\141\156\144", 
"\157\162", "\156\157\164", "\154\145\156\147\164\150", "\141\163\143\151\151", 
"\164\162\165\145", "\146\141\154\163\145", null, null, null, null, null, null, null, null, null, 
null, "\50", "\51", "\173", "\175", "\133", "\135", "\73", "\54", "\56", "\75", 
"\76", "\74", "\41", "\176", "\77", "\72", "\75\75", "\74\75", "\76\75", "\41\75", 
"\174\174", "\46\46", "\53\53", "\53", "\55", "\52", "\57", "\46", "\174", "\136", "\45", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x1fffffffce3fe01L, 
};
static final long[] jjtoSkip = {
   0x1feL, 
};
static final long[] jjtoSpecial = {
   0x1c0L, 
};
static protected JavaCharStream input_stream;
static private final int[] jjrounds = new int[64];
static private final int[] jjstateSet = new int[128];
static protected char curChar;
/** Constructor. */
public Exp1ParserTokenManager(JavaCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public Exp1ParserTokenManager(JavaCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(JavaCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 64; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(JavaCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind);
   t.kind = jjmatchedKind;
   t.image = curTokenImage;

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         matchedToken.specialToken = specialToken;
         return matchedToken;
      }
      else
      {
         if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
         {
            matchedToken = jjFillToken();
            if (specialToken == null)
               specialToken = matchedToken;
            else
            {
               matchedToken.specialToken = specialToken;
               specialToken = (specialToken.next = matchedToken);
            }
         }
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

static private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
