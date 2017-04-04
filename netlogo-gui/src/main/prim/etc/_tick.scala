// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.prim.etc

import org.nlogo.nvm.{ Command, Context }

class _tick extends Command {


  switches = true
  override def callsOtherCode = true
  override def perform(context: Context) {
    workspace.tick(context, this)
    context.ticked = true
    context.ip = next
  }
}
